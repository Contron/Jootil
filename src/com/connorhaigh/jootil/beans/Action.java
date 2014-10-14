package com.connorhaigh.jootil.beans;

public interface Action<E>
{
	/**
	 * Runs this action.
	 * @return the output
	 */
	public E run();
}