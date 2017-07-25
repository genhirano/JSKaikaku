public enum 働き方区分 {

	Z9(-1,0),
	N1(0,100),
	R1(1,100),
	L1(2,100),
	F1(3,100),
	N2(4,100),
	R2(5,100),
	L2(6,100),
	F2(7,100),
	N3(8,100),
	R3(9,100),
	L3(10,100),
	F3(11,100),
	;

	private Integer code;
	private Integer rate;

	private 働き方区分(final Integer code, final Integer rate) {
		this.code = code;
		this.rate = rate;
	}




	public static 働き方区分 get(String s) {

		switch (s) {
		case "N1":	return N1;
		case "R1":	return R1;
		case "L1":	return L1;
		case "F1":	return F1;
		case "N2":	return N2;
		case "R2":	return R2;
		case "L2":	return L2;
		case "F2":	return F2;
		case "N3":	return N3;
		case "R3":	return R3;
		case "L3":	return L3;
		case "F3":	return F3;
		case "":	return Z9;
		default : throw new RuntimeException("想定外の働き方 : " + s);
		}



	}

}
