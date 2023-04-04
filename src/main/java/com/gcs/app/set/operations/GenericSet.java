package com.gcs.app.set.operations;

import java.util.ArrayList;
import java.util.List;

public class GenericSet<T extends Identity> {
	private List<T> items;
	
	public GenericSet() {
		this.items = new ArrayList<T>();
	}
	
	public void add(T newItem) {
		if(!contains(newItem.getID())) {
			items.add(newItem);
		}else {
			System.out.println("Existing item!! Cannot add requested item to Set");
		}
	}
	
	public void displayAll() {
		System.out.println(items.size());
        for (T item : items) {
            System.out.println(item.toString());
        }
    }
	
	public int size() {
        return items.size();
    }
	
	public boolean peek(int id) {
		for (T item : items) {
            if (item.getID() == id) {
                return true;
            }
        }
        return false;
	}
	
	public T remove(int id) {
		T itemToRemove = null;
		for(T item : items) {
			if(item.getID() == id) {
				itemToRemove = item;
				items.remove(itemToRemove);
				break;
			}
		}
		return itemToRemove;
	}
	
	public boolean equals(GenericSet<T> set) {
		if(this.size() != set.size()) {
			return false;
		}
		for(T item: this.items) {
			if(!set.peek(item.getID())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean contains(int id) {
		for(T item : items) {
			if(id == item.getID()) {
				return true;
			}
		}
		return false;
	}

}

