import java.io.FileInputStream;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class HelloWorld {

	//適当なディレクトリに書き換えてください
	static final String INPUT_DIR = ".\\";
	static String xlsxFileAddress = INPUT_DIR + "Sample.xlsx";

	public static void main(String... args) throws Exception {

		List<Employee> empList = new ArrayList<>();

		try (Workbook wb = WorkbookFactory.create(new FileInputStream(xlsxFileAddress));) {

			Sheet sheet =  wb.getSheet("賃金移行シミュ");

			//タイトル行の読み込み
			Map<Integer, String> map = new TreeMap<>();
			Row titleRow = sheet.getRow(7);

			for(Integer i = 0; ; i++) {

				if("".equals(getCellValue(titleRow.getCell(i)))) {
					break;
				}

				if(null == titleRow.getCell(i).getCellComment()) {
					continue;
				}

				String titleCommnet = titleRow.getCell(i).getCellComment().getString().toString();

				if(map.containsValue(titleCommnet)) {
					throw new RuntimeException("同名のタイトルコメントがあります:" + titleCommnet);

				}

				map.put(i, titleCommnet);

			}

			for(Integer i : map.keySet()) {
				System.out.print(i + map.get(i) + "\t");
			}
			System.out.println();



			for (Integer i = 8; ; i++) {

				Row row = sheet.getRow(i);

				if("".equals(getCellValue(row.getCell(0)))) {
					break;
				}

				Map<String, String> valMap = new HashMap<>();
				for(Integer j : map.keySet()) {
					valMap.put(map.get(j), getCellValue(row.getCell(j)).toString());
				}

				Employee emp = new Employee(Double.valueOf(valMap.get("ID")).intValue(), valMap.get("氏名"));


				Employee.MonthlyBean val =  new Employee.MonthlyBean();

				val.k_kubun = 計算区分.get(valMap.get("計算区分").toString());





				//public 働き方区分 H_kubun;
				//public 管理職手当 k_teate;
				//public 資格手当 s_teate;
				//public 学位給 g_teate;




				val.所属  = valMap.get("所属").toString();


				Employee.MonthlySalary_New 試算 = new Employee.MonthlySalary_New();




				System.out.println(valMap.get("働き方区分").toString());



				試算.H_kubun = 働き方区分.get(valMap.get("働き方区分").toString());



				//ここはﾊｰﾄﾞｺｰﾃﾞｨﾝｸﾞ
				試算.所定時間_月合計 = 2080/12D;  // ≒ 173.3


				if(! valMap.get("想定時給").toString().isEmpty()) {
					試算.時給 = Double.valueOf(valMap.get("想定時給")).intValue();
				}else {
					試算.時給 = 0;
				}


				if(! valMap.get("想定月額").toString().isEmpty()) {
					試算.職能給_月合計 = Double.valueOf(valMap.get("想定月額")).intValue();
				}else {
					試算.職能給_月合計 = 0;
				}

				if(! valMap.get("想定管理職手当").toString().isEmpty()) {
					試算.管理職手当_月合計 = Double.valueOf(valMap.get("想定管理職手当")).intValue();
				}else {
					試算.管理職手当_月合計 = 0;
				}

				if(! valMap.get("想定資格手当時給").toString().isEmpty()) {
					試算.資格手当_月合計 = Double.valueOf(valMap.get("想定資格手当時給")).intValue();
				}else {
					試算.資格手当_月合計 = 0;
				}

				if(! valMap.get("想定学位給時給").toString().isEmpty()) {
					試算.学位給_月合計 = Double.valueOf(valMap.get("想定学位給時給")).intValue();
				}else {
					試算.学位給_月合計 = 0;
				}


				試算.残業時間_月合計 = 0;    // 残業時間は試算していない。
				試算.時間外手当_月合計 = 0;  // 残業時間は試算していない。

				val.budget = 試算;
				emp.salary.put(YearMonth.of(2017,5), val);


				empList.add(emp);

			}

		}catch (Exception e) {
			throw e;
		}



		for(Employee e : empList) {

			System.out.println(e.id + " " + e.name
					+ " " + e.salary.get(YearMonth.of(2017,5)).budget.時給
					+ " " + e.salary.get(YearMonth.of(2017,5)).budget.H_kubun);

		}


	}

	@SuppressWarnings(value={"deprecation"})
	private static Object getCellValue(Cell cell) {

		if(null == cell) {
			return "";
		}

		switch (cell.getCellType()) {

		case Cell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().getString();

		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_FORMULA:

			Workbook wb = cell.getSheet().getWorkbook();
			CreationHelper crateHelper = wb.getCreationHelper();
			FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();

			switch(cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			case Cell.CELL_TYPE_STRING:
				return  cell.getRichStringCellValue();
			case Cell.CELL_TYPE_ERROR:
				return  "[ERROR]";
			}

			return getCellValue(evaluator.evaluateInCell(cell));

		default:
			return "";

		}

	}


}
