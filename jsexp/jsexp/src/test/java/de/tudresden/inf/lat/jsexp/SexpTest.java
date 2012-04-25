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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class SexpTest extends TestCase {

	public void testDepth() throws SexpParserException, IOException {
		String testStr = " (( defun test () \"hi there\"))";
		ByteArrayInputStream input = new ByteArrayInputStream(
				testStr.getBytes());
		Sexp parsedExpression = SexpFactory.parse(input);
		Sexp sexp1 = SexpFactory.newNonAtomicSexp();
		sexp1.add(SexpFactory.newAtomicSexp("defun"));
		sexp1.add(SexpFactory.newAtomicSexp("test"));
		sexp1.add(SexpFactory.newNonAtomicSexp());
		sexp1.add(SexpFactory.newAtomicSexp("\"hi there\""));
		Sexp expectedExpression = SexpFactory.newNonAtomicSexp();
		expectedExpression.add(sexp1);
		assertEquals(expectedExpression, parsedExpression);
	}

	public void testEmptyString() throws SexpParserException, IOException {
		String testStr = "";
		try {
			SexpFactory.parse(new ByteArrayInputStream(testStr.getBytes()));
			assertTrue(false);
		} catch (SexpParserException e) {
			assertTrue(true);
		}
	}

	public void testGet() throws SexpParserException, IOException {
		String testStr = "((elem-01-01 elem-01-02) elem-02 (elem-03-01 elem-03-02 elem-03-03))";
		Sexp parsedExpr = SexpFactory.parse(new ByteArrayInputStream(testStr
				.getBytes()));
		Sexp expectedElem03 = SexpFactory.newNonAtomicSexp();
		expectedElem03.add(SexpFactory.newAtomicSexp("elem-03-01"));
		expectedElem03.add(SexpFactory.newAtomicSexp("elem-03-02"));
		expectedElem03.add(SexpFactory.newAtomicSexp("elem-03-03"));
		assertEquals(SexpFactory.newAtomicSexp("elem-01-01"), parsedExpr.get(0)
				.get(0));
		assertEquals(SexpFactory.newAtomicSexp("elem-01-02"), parsedExpr.get(0)
				.get(1));
		assertEquals(SexpFactory.newAtomicSexp("elem-02"), parsedExpr.get(1));
		assertEquals(expectedElem03, parsedExpr.get(2));
		try {
			parsedExpr.get(3);
			assertTrue(false);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		try {
			(SexpFactory.newAtomicSexp("example")).get(0);
			assertTrue(false);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	public void testIndentedString() {
		Sexp aux = SexpFactory.newNonAtomicSexp();
		aux.add(SexpFactory.newAtomicSexp("defun"));
		aux.add(SexpFactory.newAtomicSexp("mark-told-subsumers-and-ancestors"));
		Sexp aux1 = SexpFactory.newNonAtomicSexp();
		aux1.add(SexpFactory.newAtomicSexp("c"));
		aux1.add(SexpFactory.newAtomicSexp("subsumer"));
		aux.add(aux1);
		aux.add(SexpFactory
				.newAtomicSexp("\"Mark as subsumer of c all told subsumers and their ancestors in the hierarchy\""));
		Sexp aux2 = SexpFactory.newNonAtomicSexp();
		aux2.add(SexpFactory.newAtomicSexp("dolist"));
		Sexp aux2_1 = SexpFactory.newNonAtomicSexp();
		aux2_1.add(SexpFactory.newAtomicSexp("x"));
		Sexp aux2_1_1 = SexpFactory.newNonAtomicSexp();
		aux2_1_1.add(SexpFactory.newAtomicSexp("c-told-subsumers"));
		aux2_1_1.add(SexpFactory.newAtomicSexp("c"));
		aux2_1.add(aux2_1_1);
		aux2.add(aux2_1);
		Sexp aux2_2 = SexpFactory.newNonAtomicSexp();
		aux2_2.add(SexpFactory.newAtomicSexp("unless"));
		Sexp aux2_2_1 = SexpFactory.newNonAtomicSexp();
		aux2_2_1.add(SexpFactory.newAtomicSexp("eq"));
		Sexp aux2_2_1_1 = SexpFactory.newNonAtomicSexp();
		aux2_2_1_1.add(SexpFactory.newAtomicSexp("c-marked"));
		aux2_2_1_1.add(SexpFactory.newAtomicSexp("x"));
		aux2_2_1.add(aux2_2_1_1);
		aux2_2_1.add(SexpFactory.newAtomicSexp("subsumer"));
		aux2_2.add(aux2_2_1);
		Sexp aux2_2_2 = SexpFactory.newNonAtomicSexp();
		aux2_2_2.add(SexpFactory.newAtomicSexp("when"));
		Sexp aux2_2_2_1 = SexpFactory.newNonAtomicSexp();
		aux2_2_2_1.add(SexpFactory.newAtomicSexp("c-classified"));
		aux2_2_2_1.add(SexpFactory.newAtomicSexp("x"));
		aux2_2_2.add(aux2_2_2_1);
		Sexp aux2_2_2_2 = SexpFactory.newNonAtomicSexp();
		aux2_2_2_2.add(SexpFactory.newAtomicSexp("mark-ancestors"));
		aux2_2_2_2.add(SexpFactory.newAtomicSexp("x"));
		aux2_2_2_2.add(SexpFactory.newAtomicSexp("subsumer"));
		aux2_2_2.add(aux2_2_2_2);
		aux2_2.add(aux2_2_2);
		Sexp aux2_2_3 = SexpFactory.newNonAtomicSexp();
		aux2_2_3.add(SexpFactory
				.newAtomicSexp("mark-told-subsumers-and-ancestors"));
		aux2_2_3.add(SexpFactory.newAtomicSexp("x"));
		aux2_2_3.add(SexpFactory.newAtomicSexp("subsumer"));
		aux2_2.add(aux2_2_3);
		aux2.add(aux2_2);
		aux.add(aux2);
		String str = "(defun mark-told-subsumers-and-ancestors "
				+ "\n  (c subsumer) \"Mark as subsumer of c all told subsumers and their ancestors in the hierarchy\" "
				+ "\n  (dolist " + "\n    (x "
				+ "\n      (c-told-subsumers c)) " + "\n    (unless "
				+ "\n      (eq " + "\n        (c-marked x) subsumer) "
				+ "\n      (when " + "\n        (c-classified x) "
				+ "\n        (mark-ancestors x subsumer)) "
				+ "\n      (mark-told-subsumers-and-ancestors x subsumer))))";
		assertEquals(str, aux.toIndentedString());
	}

	public void testMissingParenthesis() throws SexpParserException,
			IOException {
		String testStr = "(( defun test () \"hi there\")( a s ";
		try {
			SexpFactory.parse(new ByteArrayInputStream(testStr.getBytes()));
			assertTrue(false);
		} catch (SexpParserException e) {
			assertTrue(true);
		}
		testStr = "(( defun test () \"hi there\")";
		try {
			SexpFactory.parse(testStr);
			assertTrue(false);
		} catch (SexpParserException e) {
			assertTrue(true);
		}
		testStr = ") defun test () \"hi there\")";
		try {
			SexpFactory.parse(new ByteArrayInputStream(testStr.getBytes()));
			assertTrue(false);
		} catch (SexpParserException e) {
			assertTrue(true);
		}
	}

	public void testParsing() throws SexpParserException, IOException {
		String testStr = "( defun test () \"hi there\")";
		Sexp parsedExpression = SexpFactory.parse(testStr);
		Sexp expectedExpression = SexpFactory.newNonAtomicSexp();
		expectedExpression.add(SexpFactory.newAtomicSexp("defun"));
		expectedExpression.add(SexpFactory.newAtomicSexp("test"));
		expectedExpression.add(SexpFactory.newNonAtomicSexp());
		expectedExpression.add(SexpFactory.newAtomicSexp("\"hi there\""));
		assertEquals(expectedExpression, parsedExpression);
	}

	public void testPlainString() throws SexpParserException, IOException {
		String testStr = "test";
		Sexp expectedExpr = SexpFactory.newAtomicSexp("test");
		Sexp parsedExpr = SexpFactory.parse(new ByteArrayInputStream(testStr
				.getBytes()));
		assertEquals(expectedExpr, parsedExpr);
	}

}
