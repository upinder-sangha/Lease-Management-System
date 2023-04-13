package com.gcs.app.set.operations;

public class SetDriver {
	public static void main(String[] args) {
		GenericDataClass obj1 = new GenericDataClass("Test 1", 1);
		GenericDataClass obj2 = new GenericDataClass(1, 2);
		GenericDataClass obj3 = new GenericDataClass(1.1, 3);
		GenericDataClass obj4 = new GenericDataClass(true, 2); // Cannot add requested item to Set: Existing ID!!
		GenericSet set = new GenericSet();
		set.add(obj1);
		set.add(obj2);
		set.add(obj3);
		set.add(obj4);
		set.displayAll();	
	}
}
