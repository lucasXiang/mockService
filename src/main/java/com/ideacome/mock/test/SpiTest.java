package com.ideacome.mock.test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {
	public static void main(String[] args) {
		ServiceLoader<IA> spiLoader = ServiceLoader.load(IA.class); 
        Iterator<IA> iaIterator = spiLoader.iterator(); 
        while (iaIterator.hasNext()) { 
            iaIterator.next().print(); 
        } 
	}
}
