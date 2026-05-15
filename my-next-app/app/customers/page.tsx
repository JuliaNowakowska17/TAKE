import NewCustomerForm from "./_components/NewCustomerForm";
import CustomersTable from "./_components/CustomersTable";

export const dynamic = "force-dynamic";

export default function CustomersPage() {
  return (
    <>
      <NewCustomerForm />

      <p>&nbsp;</p>

      <CustomersTable />
    </>
  );
}