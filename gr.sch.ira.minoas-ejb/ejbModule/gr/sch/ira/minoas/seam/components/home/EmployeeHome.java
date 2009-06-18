package gr.sch.ira.minoas.seam.components.home;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.EmploymentType;

/**
 * @author <a href="mailto:fsla@forthnet.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name("employeeHome")
public class EmployeeHome extends MinoasEntityHome<Employee> {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.jboss.seam.framework.Home#getInstance()
	 */
	@Override
	@Factory(value = "employee", scope = ScopeType.PAGE)
	public Employee getInstance() {
		return (Employee) super.getInstance();
	}

	public boolean hasEmployment() {
		return getInstance().getCurrentEmployment() != null;
	}

	public boolean hasRegularEmployment() {
		return hasEmployment() && getInstance().getCurrentEmployment().getType().equals(EmploymentType.REGULAR);
	}

	public boolean hasDeputyEmployment() {
		return hasEmployment() && getInstance().getCurrentEmployment().getType().equals(EmploymentType.DEPUTY);
	}

}