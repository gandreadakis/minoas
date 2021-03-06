/**
 * 
 */
package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.EmployeeType;
import gr.sch.ira.minoas.model.employee.Person;
import gr.sch.ira.minoas.model.employee.RegularEmployeeInfo;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeUtil")
@Scope(ScopeType.STATELESS)
public class EmployeeUtil extends BaseSeamComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getRegistryID(Employee employee) {
		Employee aemployee = employee;
		if (aemployee.getRegularDetail() != null) {
			return aemployee.getRegularDetail().getRegistryID();
		} else
			return null;

	}

	public boolean hasSecondment(Employee employee) {
		if (employee != null && employee.getCurrentEmployment() != null) {
			return employee.getCurrentEmployment().getSecondment() != null;
		} else
			return false;
	}

	public boolean isDeputy(Employee employee) {
		return employee.getType().equals(EmployeeType.DEPUTY);
	}

	public boolean isHourlyPaid(Employee employee) {
		return employee.getType().equals(EmployeeType.HOURLYPAID);
	}

	public boolean isRegular(Employee employee) {
		return employee.getType().equals(EmployeeType.REGULAR);
	}

	public String prettyFormat(Employee employee) {
		if (employee != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(employee.getLastName());
			sb.append(" ");
			sb.append(employee.getFirstName());
			sb.append(" του ");
			sb.append(employee.getFatherName());
			if (employee.getCurrentEmployment() != null) {
				sb.append(" (");
				sb.append(employee.getCurrentEmployment().getSpecialization().getId());
				sb.append(" )");
			}
			return sb.toString();
		} else
			return null;
	}
	
	public String prettyFormatForRegularEmployee(Employee employee) {
		StringBuffer sb = new StringBuffer();
		if(employee!=null && isRegular(employee)) {
			RegularEmployeeInfo info = employee.getRegularDetail();
			if(info!=null) {
				sb.append("(");
				sb.append(info.getRegistryID());
				sb.append(") ");
				sb.append(employee.getLastName());
				sb.append(" ");
				sb.append(employee.getFirstName());
				sb.append(" του ");
				sb.append(employee.getFatherName());
				if (employee.getLastSpecialization() != null) {
					sb.append(" - (");
					sb.append(employee.getLastSpecialization().getId());
					sb.append(" )");
				}
				if (employee.getCurrentEmployment() != null) {
					sb.append(" - [");
					sb.append(employee.getCurrentEmployment().getSchool().getTitle());
					sb.append(" ]");
				}
			}
		} else {
			sb.append("Ο εκπαιδευτικός ");
			sb.append(employee.toString());
			sb.append(" δεν είναι μόνιμος");
		}
		return sb.toString();
	}

	public String prettyFormatNoSpecialization(Person employee) {
		if (employee != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(employee.getLastName());
			sb.append(" ");
			sb.append(employee.getFirstName());
			sb.append(" του ");
			sb.append(employee.getFatherName());
			return sb.toString();
		} else
			return null;

	}
}
