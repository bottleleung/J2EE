package com.ocs.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ocs.daos.DAOException;
import com.ocs.entities.Cost;
import com.ocs.interfaces.daos.ICostDAO;
import com.ocs.interfaces.services.ICostService;
import com.ocs.services.ServiceException;

/**
 * 处理资费的Action
 * @author Leslie Leung
 */
@Scope("prototype")
@Controller
public class CostAction {
	private int id;
	private boolean isStartSuccess;	//是否开通成功
	private String name;
	private int page = 1;	//默认打开第一页
	private int pageSize;	//页容量
	private int totalPages;		//总页数
	private Cost cost;	//一条资费数据
	private List<Cost> costs;	//查询出来的一组数据
	private boolean isCostRepeat;	//关于cost是否重复的信息
	
	@Autowired
	private ICostService costService;
	@Autowired
	private ICostDAO costDAO;
	
	/**
	 * 跳转到资费详情页面
	 * @return
	 */
	public String toCostDetail() {
		try {
			cost = costService.detail(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findDetailSuccess";
	}
	
	/**
	 * 启用某资费
	 * @return
	 */
	public String start() {
		try {
			costService.start(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "startSuccess";
	}
	
	/**
	 * 控制其跳转到新增资费页面
	 * @return
	 */
	public String toAddCost() {
		return "toAddCost";
	}
	
	/**
	 * 修改某cost
	 * @return
	 */
	public String updateCost() {
		try {
			costService.update(cost);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "updateCostSuccess";
	}
	
	/**
	 * 跳转到修改cost页面，这过程需要把当前那组资费的所有数据显示在对应的表单域上
	 * @return
	 */
	public String toUpdateCost() {
		try {
			cost = costDAO.findById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "toUpdateCostSuccess";
	}
	
	/**
	 * 调用分页查询
	 * @return
	 */
	public String find() {
		try {
			costs = costService.find(page, pageSize);
			totalPages = costService.findTotalPages(pageSize);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "findSuccess";
	}
	
	/**
	 * 新增时检查Cost是否重复
	 * @return
	 */
	public String checkCostRepeat() {
		try {
			if(costDAO.findByName(id, name) == true) {//如果输入名称存在
				isCostRepeat = true;
			} else {//输入名称不存在
				isCostRepeat = false;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "costChecked";
	}
	
	/**
	 * 执行删除资费数据操作
	 * @return
	 */
	public String delete() {
		try {
			costService.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "deleteSuccess";
	}
	
	/**
	 * 把一组资费数据添加到数据表中
	 * @return
	 */
	public String add() {
		try {
			costService.add(cost);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "addSuccess";
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public boolean getIsStartSuccess() {
		return isStartSuccess;
	}

	public void setStartSuccess(boolean isStartSuccess) {
		this.isStartSuccess = isStartSuccess;
	}

	public boolean getIsCostRepeat() {
		return isCostRepeat;
	}

	public void setCostRepeat(boolean isCostRepeat) {
		this.isCostRepeat = isCostRepeat;
	}

	public ICostService getCostService() {
		return costService;
	}

	public void setCostService(ICostService costService) {
		this.costService = costService;
	}

	public ICostDAO getCostDAO() {
		return costDAO;
	}

	public void setCostDAO(ICostDAO costDAO) {
		this.costDAO = costDAO;
	}

	
}
