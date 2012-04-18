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

/**
 * This is a unit in parsing for S-expressions. A text between quotes is
 * considered as one single token.
 * 
 * @author Julian Mendez
 */
class Token {
	public static final char escapeChar = '\\';
	public static final char commentChar = ';';
	public static final char leftParenthesisChar = '(';
	public static final char rightParenthesisChar = ')';
	public static final char quotationMarkChar = '\"';
	public static final char verticalBarChar = '|';

	private StringBuffer sbuf = new StringBuffer();
	private int location = -1;

	public Token() {
	}

	public Token(String str, int loc) {
		if (str == null) {
			throw new IllegalArgumentException(
					"Cannot create a token using a null string.");
		}
		this.sbuf = new StringBuffer(str);
		this.location = loc;
	}

	public void append(char ch) {
		this.sbuf.append(ch);
	}

	public void append(String str) {
		if (str != null) {
			this.sbuf.append(str);
		}
	}

	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof Token) {
			Token other = (Token) o;
			ret = getText().equals(other.getText())
					&& getLocation() == other.getLocation();
		}
		return ret;
	}

	public int getLocation() {
		return this.location;
	}

	public String getText() {
		return this.sbuf.toString();
	}

	public int hashCode() {
		return this.getText().hashCode();
	}

	public boolean isComment() {
		return getText().startsWith("" + commentChar);
	}

	public boolean isLeftParenthesis() {
		return getText().equals("" + leftParenthesisChar);
	}

	public boolean isQuotationMarkToken() {
		return getText().startsWith("" + quotationMarkChar)
				&& getText().endsWith("" + quotationMarkChar);
	}

	public boolean isRightParenthesis() {
		return getText().equals("" + rightParenthesisChar);
	}

	public boolean isVerticalBarToken() {
		return getText().startsWith("" + verticalBarChar)
				&& getText().endsWith("" + verticalBarChar);
	}

	public int length() {
		return this.sbuf.length();
	}

	public void setLocation(int loc) {
		this.location = loc;
	}

	public void setText(String str) {
		if (str == null) {
			throw new IllegalArgumentException(
					"Cannot create a token using a null string.");
		}
		this.sbuf = new StringBuffer(str);
	}

	public String toString() {
		return getText() + ":" + getLocation();
	}
}
