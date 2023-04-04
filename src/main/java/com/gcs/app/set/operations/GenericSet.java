package com.gcs.app.set.operations;

import java.util.ArrayList;
import java.util.List;

public class GenericSet<T extends Identity> {
	private List<T> items;
	
	public GenericSet() {
		this.items = new ArrayList<T>();
	}
	
	public void add(T newItem) {
		for(T item : items) {
			if(newItem.getID() == item.getID()) {
				System.out.println("Existing item!! Cannot add requested item to Set");
				break;
			}
			else
				items.add(newItem);
		}
		
	}
	
	public void displayAll() {
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

}

