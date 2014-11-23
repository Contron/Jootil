package com.connorhaigh.jootil.core;

public interface Action<E>
{
	/**
	 * Runs this action.
	 * @return the output
	 */
	public E run();
}