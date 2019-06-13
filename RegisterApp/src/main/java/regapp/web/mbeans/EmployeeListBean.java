package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.view.EmployeeListViewModel;
import regapp.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;
    private BigDecimal total;
    private BigDecimal average;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper){
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(empl -> this.modelMapper.map(empl,EmployeeListViewModel.class))
                .collect(Collectors.toList());
        this.total = this.employees.stream()
                .map(empl -> empl.getSalary())
                .reduce((a, b) -> a.add(b))
                .orElse(BigDecimal.ZERO);

        this.average = this.total.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : this.total.divide(new BigDecimal(this.employees.size()));
    }

    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }
}
