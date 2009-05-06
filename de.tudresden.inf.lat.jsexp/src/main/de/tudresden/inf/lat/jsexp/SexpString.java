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

import java.util.Iterator;

/**
 * Represents an atomic S-expression.
 * 
 * @author Julian Mendez
 * 
 */
public class SexpString implements Sexp {

	private String rep = null;

	protected SexpString(String text) {
		this.rep = text;
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#add(Sexp)
	 */
	public void add(Sexp item) {
		throw new IndexOutOfBoundsException();
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#get(int)
	 */
	public Sexp get(int index) {
		throw new IndexOutOfBoundsException();
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#getDepth()
	 */
	public int getDepth() {
		return 0;
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#getLength()
	 */
	public int getLength() {
		return 0;
	}

	private String getText() {
		return this.rep;
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#isAtomic()
	 */
	public boolean isAtomic() {
		return true;
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#iterator()
	 */
	public Iterator<Sexp> iterator() {
		return null;
	}

	/**
	 * @see de.tudresden.inf.lat.jsexp.Sexp#toIndentedString()
	 */
	public String toIndentedString() {
		return toString();
	}

	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof SexpString) {
			SexpString other = (SexpString) o;
			ret = getText().equals(other.getText());
		}
		return ret;
	}

	public int hashCode() {
		return getText().hashCode();
	}

	public String toString() {
		return getText();
	}
}
