package jp.co.sample.emp_management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	@ModelAttribute
	public InsertEmployeeForm setInsertForm() {
		return new InsertEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：入力された文字列を含んだ従業員一覧を表示する（空文字の場合は全検索）
	/////////////////////////////////////////////////////
	/**
	 * 入力された文字列を含んだ従業員一覧画面を出力します.
	 * 
	 * @param name  検索したい従業員名
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/searchEmployees")
	public String searchEmployees(String name, Model model) {
		List<Employee> employeeList = employeeService.searchEmployees(name);

		if (employeeList.size() == 0) {
			employeeList = employeeService.showList();
			model.addAttribute("searchEmpty", "1件もありませんでした");
			return "employee/List";
		}

		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		Date hireDate = employee.getHireDate();
		model.addAttribute("hireDate", hireDate);

		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	@RequestMapping("/toInsert")
	public String toInsert(Model model) {

		// 性別のリスト
		List<String> genderList = new ArrayList<>();
		genderList.add("男");
		genderList.add("女");

		model.addAttribute("genderList", genderList);

		// 人数のリスト
		List<Integer> dependentsCountList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			dependentsCountList.add(i);
		}

		model.addAttribute("dependentsCountList", dependentsCountList);

		return "employee/insert";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

	@RequestMapping("/insert")
	public String insert(@Validated InsertEmployeeForm form, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return toInsert(model);
		}

		return "redirect:/employee/showList";
	}
}
