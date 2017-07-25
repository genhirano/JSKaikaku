
public enum 管理職手当 {

	部長等(1,0),
	課長1(1,250),
	課長2(1,0),
	課長3(1,0),
	主任1(1,0),
	主任2(1,0),
	主任3(1,0),
	副主任1(1,0),
	副主任2(1,0),
	副主任3(1,0),
	;

	private Integer code;
	private Integer hourlyWage;
	private 管理職手当(final Integer code, final Integer hourlyWage) {
		this.code = code;
		this.hourlyWage = hourlyWage;
	}


}
