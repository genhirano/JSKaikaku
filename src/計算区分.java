
public enum 計算区分 {

	月例給(0),
	年俸制(1),
	日給制(2),

	正職員(101),
	準職員(102),
	パート(103),
	パート_利用者(104),
	;

	private Integer code;
	private 計算区分(final Integer code) {
		this.code = code;
	}


	public static 計算区分 get(String s) {


		switch (s) {
		case "正職員":
			return 正職員;
		case "準職員":
			return 準職員;
		case "パート":
			return パート;
		case "パート（利用者）":
		case "パート(利用者）":
			return パート_利用者;




		default:
			;
		}
		throw new RuntimeException("想定外の計算区分 : " + s);
	}


}
