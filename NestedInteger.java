import java.util.*;

public class NestedInteger implements NestedIntegerInterface {
	List<NestedIntegerInterface> _list;
	int _integerValue;
	boolean _isInteger;
	
	// Constructor initializes an empty nested list.
	public NestedInteger() {
		_isInteger=false;
		_list=new ArrayList<>();
	}

	// Constructor initializes a single integer.
	public NestedInteger(int value) {
		_isInteger=true;
		_integerValue=value;
	}
	
	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger() {
		return _isInteger;
	}

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger() {
		if(!_isInteger) {
			return null;
		}
		return _integerValue;
	}

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value) {
		if(!_isInteger) {
			_isInteger=true;
		}
		_integerValue=value;
	}

	// Set this NestedInteger to hold a nested list and adds a nested integer to it.
	public void add(NestedIntegerInterface ni) {
		if(_isInteger) {
			_list=new ArrayList<>();
			_list.add(ni);
			_isInteger=false;
		}
		else {
			_list.add(ni);
		}
	}

	// @return the nested list that this NestedInteger holds, if it holds a nested
	// list
	// Return null if this NestedInteger holds a single integer
	public List<NestedIntegerInterface> getList() {
		if(_isInteger) {
			return null;
		}
		return _list;
	}
}
