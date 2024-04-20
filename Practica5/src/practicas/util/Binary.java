package practicas.util;

public class Binary extends Object {

	Integer value;
	
	Binary(Integer value) throws Exception{
		
		if( isValueBinary(value) ) {
			this.value = value;
		}else {
			throw new Exception("Assignment to variable is not binary");
		}
		
	}
	
	
	public Integer getIntegerValue() {
		return value;
	}
	
	public Boolean getLogicValue() {
		return value==1;
	}
	
	public void setValue(Integer value) {
		if(isValueBinary(value) ) {
			this.value = value;
		}
	}
	
	public static Binary of(Integer value)  {
		try {
			return new Binary(value);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isValueBinary(Integer value) {
		return (value >= 0 && value <= 1)?true:false;
	}
	
	@Override
	public boolean equals(Object obj) {
		Binary binaryObj = (Binary) obj;
		return this.value == binaryObj.getIntegerValue();
	}
	
	
}
