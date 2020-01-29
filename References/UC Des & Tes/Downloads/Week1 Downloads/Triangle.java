public class Triangle {
	float base, height, hypo;
	public Triangle(){
		base = 3;
		height = 4;
		hypo = 5;
	}
		
	public Triangle(float b, float h, float hy){
		base = b;
		height = h;
		hypo = hy;
	}
	
	public boolean isRightTriangle(){
		if(Math.sqrt(base * base + height * height) == hypo){
			return true;
		}
		else{
			return false;
		}
	}
	
	public float calcArea(){
		return (1/2 * base * height); 
	}
	
	public float calcPerimeter(){
		return (base + hypo);
	}

}
