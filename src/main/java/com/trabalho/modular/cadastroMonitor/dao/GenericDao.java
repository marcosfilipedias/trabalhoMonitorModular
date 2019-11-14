package com.trabalho.modular.cadastroMonitor.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract interface GenericDao<T,K> {

	public T get(K id);
	public boolean create(T t);
	public List<T> read() throws FileNotFoundException, NumberFormatException, IOException;
	public void update(T t) throws NumberFormatException, IOException;
	public void delete(T t) throws NumberFormatException, IOException;
}
