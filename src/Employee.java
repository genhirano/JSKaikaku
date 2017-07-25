import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Employee {

	public Integer id;
	public String name;

	public Map<YearMonth, MonthlyBean> salary = new HashMap<>();

	public static class MonthlyBean {
		public String 所属;
		public 計算区分 k_kubun;
		public MonthlySalary currentAmano;
		public MonthlySalary_New budget;
		public MonthlySalary_New performance;
	}

	static class MonthlySalary{
		public Integer 時給;
		public Integer 職能給_月合計;

		public Double 所定時間_月合計;

		public Integer 管理職手当_月合計;
		public Integer 資格手当_月合計;
		public Integer 学位給_月合計;

		public Integer 残業時間_月合計;
		public Integer 時間外手当_月合計;


	}

	static class  MonthlySalary_New extends MonthlySalary{
		public 働き方区分 H_kubun;
		public 管理職手当 k_teate;
		public 資格手当 s_teate;
		public 学位給 g_teate;

	}


	public Employee(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}


	public MonthlyBean addMonthSalary(YearMonth yearMonth, MonthlySalary currentAmano, MonthlySalary_New budget, MonthlySalary_New performance) {

		MonthlyBean mb = new MonthlyBean();

		mb.currentAmano = currentAmano;
		mb.budget = budget;
		mb.performance = performance;

		this.salary.put(yearMonth, mb);

		return mb;

	}




}
