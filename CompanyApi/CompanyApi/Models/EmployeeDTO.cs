namespace CompanyApi.Models;

public class EmployeeDTO
{
    public int EmployeeId { get; set; }

    public string FirstName { get; set; } = null!;

    public string LastName { get; set; } = null!;

    public int? ManagerId { get; set; }

    public decimal? Salary { get; set; }

    public decimal? Bonus { get; set; }

    public int? DepartmentId { get; set; }
}