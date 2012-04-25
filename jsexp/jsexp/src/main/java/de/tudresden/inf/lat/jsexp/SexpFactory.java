/*
 * Copyright (C) 2009, 2012 Julian Mendez
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
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Creates new S-expressions.
 * 
 * @author Julian Mendez
 */
public class SexpFactory {

	/**
	 * Creates a new atomic S-expression.
	 * 
	 * @param str
	 *            content of the atomic S-expression
	 * @return the atomic S-expression
	 */
	public static Sexp newAtomicSexp(String str) {
		return new SexpString(str);
	}

	/**
	 * Creates a new empty non-atomic S-expression.
	 * 
	 * @return the empty non-atomic S-expression
	 */
	public static Sexp newNonAtomicSexp() {
		return new SexpList();
	}

	/**
	 * Creates a new S-expression read from a reader.
	 * 
	 * @param in
	 *            input to read the expression.
	 * @return the parsed S-expression
	 * @throws SexpParserException
	 *             if the expression cannot be parsed.
	 * @throws IOException
	 *             if there is any problem reading the input.
	 */
	public static Sexp parse(Reader in) throws SexpParserException, IOException {
		Sexp ret = null;
		List<Token> tokenList = SexpTokenizer.tokenize(in);
		if (tokenList.size() == 0) {
			throw new SexpParserException("Empty expression cannot be parsed.");
		} else if (tokenList.size() == 1) {
			ret = new SexpString(tokenList.get(0).getText());
		} else if (tokenList.size() > 1) {
			ret = new SexpList(tokenList);
		}
		if (ret == null) {
			throw new SexpParserException("Expression '" + tokenList.toString()
					+ "' cannot be parsed.");
		}
		return ret;
	}

	/**
	 * Creates a new S-expression read from a string.
	 * 
	 * @param str
	 *            string to read the expression.
	 * @return the parsed S-expression
	 * @throws SexpParserException
	 *             if the expression cannot be parsed.
	 */
	public static Sexp parse(String str) throws SexpParserException {
		Sexp ret = null;
		try {
			ret = parse(new StringReader(str));
		} catch (IOException e) {
			throw new SexpParserException(e);
		}
		return ret;
	}

}
