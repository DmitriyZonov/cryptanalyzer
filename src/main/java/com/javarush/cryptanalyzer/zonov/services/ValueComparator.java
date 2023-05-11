package com.javarush.cryptanalyzer.zonov.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ValueComparator<Character, Integer extends Comparable> implements Comparator<Character> {
        private Map<Character, Integer> map;

        public ValueComparator(Map<Character, Integer> map) {
            this.map = new HashMap<>(map);
        }

        @Override
        public int compare(Character s1, Character s2) {
            return map.get(s2).compareTo(map.get(s1));
        }
}
