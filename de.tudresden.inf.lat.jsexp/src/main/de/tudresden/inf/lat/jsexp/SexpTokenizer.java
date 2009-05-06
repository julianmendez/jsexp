/*
 * Copyright 2009 Julian Mendez
 *
 *
 * This file is part of jsexp.
 *
 * jsexp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jsexp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsexp.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package de.tudresden.inf.lat.jsexp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A LispTokenizer splits a string read from an InputStream in a list of tokens.
 * In this way, blank spaces are omitted and texts between quotation marks are
 * considered as one token. It is implemented as a finite state machine.
 * 
 * @author Julian Mendez
 */
class SexpTokenizer {

	private enum State {
		SYMBOL, QMARK, QMARK_BSLASH, VBAR, VBAR_BSLASH, COMMENT
	}

	public static final char escapeChar = Token.escapeChar;
	public static final char commentChar = Token.commentChar;
	public static final char leftParenthesisChar = Token.leftParenthesisChar;
	public static final char rightParenthesisChar = Token.rightParenthesisChar;
	public static final char quotationMarkChar = Token.quotationMarkChar;
	public static final char verticalBarChar = Token.verticalBarChar;
	public static final char spaceChar = ' ';
	public static final char returnChar = '\r';
	public static final char newLineChar = '\n';

	public static final char tabChar = '\t';

	public static List<Token> tokenize(InputStream in)
			throws SexpParserException, IOException {
		SexpTokenizer tokenizer = new SexpTokenizer();
		boolean finished = false;
		int ch = in.read();
		while (!finished && ch != -1) {
			finished = tokenizer.readChar((char) ch);
			if (!finished) {
				ch = in.read();
			}
		}
		tokenizer.finalize();
		return tokenizer.getParsedTokens();
	}

	private State state = State.SYMBOL;
	private Token currentToken = new Token();
	private List<Token> tokenList = new ArrayList<Token>();
	private int lineNumber = 1;

	private int depth = 0;

	private SexpTokenizer() {
	}

	protected void finalize() throws SexpParserException {
		flush();
		if (this.state.equals(State.QMARK)
				|| this.state.equals(State.QMARK_BSLASH)) {
			throw new SexpParserException("Missing quotation mark at line "
					+ this.lineNumber);
		}
		if (this.depth != 0) {
			throw new SexpParserException("Unbalanced parenthesis at line "
					+ this.lineNumber + ".");
		}
	}

	protected void flush() {
		if (this.currentToken.length() > 0) {
			this.currentToken.setLocation(lineNumber);
			this.tokenList.add(this.currentToken);
			this.currentToken = new Token();
		}
	}

	protected Token getCurrentToken() {
		return this.currentToken;
	}

	protected List<Token> getParsedTokens() {
		return this.tokenList;
	}

	protected boolean readChar(char ch) {
		boolean ret = false;
		switch (this.state) {
		case SYMBOL:
			readSymbol(ch);
			break;
		case QMARK:
			readQuotationMark(ch);
			break;
		case QMARK_BSLASH:
			readQuotationMarkBackslash(ch);
			break;
		case VBAR:
			readVerticalBar(ch);
			break;
		case VBAR_BSLASH:
			readVerticalBarBackslash(ch);
			break;
		case COMMENT:
			readComment(ch);
			break;
		}
		ret = (this.depth <= 0 && this.tokenList.size() > 0);
		return ret;
	}

	protected void readComment(char ch) {
		if (ch == newLineChar) {
			flush();
			this.lineNumber++;
			this.state = State.SYMBOL;
		} else {
			this.currentToken.append(ch);
		}
	}

	protected void readQuotationMark(char ch) {
		this.currentToken.append(ch);
		if (ch == newLineChar) {
			this.lineNumber++;
		} else if (ch == escapeChar) {
			this.state = State.QMARK_BSLASH;
		} else if (ch == quotationMarkChar) {
			flush();
			this.state = State.SYMBOL;
		}
	}

	protected void readQuotationMarkBackslash(char ch) {
		this.currentToken.append(ch);
		if (ch == newLineChar) {
			this.lineNumber++;
		}
		this.state = State.QMARK;
	}

	protected void readSymbol(char ch) {
		if (ch == newLineChar) {
			flush();
			this.lineNumber++;
		} else if (ch == spaceChar || ch == tabChar || ch == returnChar) {
			flush();
		} else if (ch == commentChar) {
			flush();
			this.currentToken.append(ch);
			this.state = State.COMMENT;
		} else if (ch == leftParenthesisChar) {
			this.depth++;
			flush();
			this.currentToken.append(ch);
			flush();
		} else if (ch == rightParenthesisChar) {
			this.depth--;
			flush();
			this.currentToken.append(ch);
			flush();
		} else if (ch == quotationMarkChar) {
			flush();
			this.currentToken.append(ch);
			this.state = State.QMARK;
		} else if (ch == verticalBarChar) {
			flush();
			this.currentToken.append(ch);
			this.state = State.VBAR;
		} else {
			this.currentToken.append(ch);
		}
	}

	protected void readVerticalBar(char ch) {
		this.currentToken.append(ch);
		if (ch == newLineChar) {
			this.lineNumber++;
		} else if (ch == escapeChar) {
			this.state = State.VBAR_BSLASH;
		} else if (ch == verticalBarChar) {
			flush();
			this.state = State.SYMBOL;
		}
	}

	protected void readVerticalBarBackslash(char ch) {
		this.currentToken.append(ch);
		if (ch == newLineChar) {
			this.lineNumber++;
		}
		this.state = State.VBAR;
	}
}
