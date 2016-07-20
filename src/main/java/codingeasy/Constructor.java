package codingeasy;

public class Constructor extends Method {

	public Constructor(Type type) {
		super(type, type.getSimpleName());
	}
	
	@Override
	public Method type(String type) {
		throw new IllegalArgumentException("Constructors has not a return type");
	}

}
