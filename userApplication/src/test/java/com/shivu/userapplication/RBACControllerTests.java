package com.shivu.userapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.shivu.userapplication.controller.RBACController;
import com.shivu.userapplication.repository.DepartmentRepository;
import com.shivu.userapplication.repository.RoleRepository;
import com.shivu.userapplication.repository.UserRepository;


@SpringBootTest
public class RBACControllerTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private RBACController rbacController;

    @BeforeEach
    @SuppressWarnings("deprecation")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHR() {
        String expected = "HR level Access";
        String actual = rbacController.getHR();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPayroll() {
        String expected = "Payroll level Access";
        String actual = rbacController.getPayroll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFinance() {
        String  expected = "Finance level Access";
        String actual = rbacController.getFinance();
        assertEquals(expected,actual);
       
    }

    @Test
    public void testGetSales() {
        String  expected = "Sales level Access";
        String actual = rbacController.getSales();
        assertEquals(expected,actual);
    }

    @Test
    public void  testGetIT() {
        String  expected = "IT level Access";
        String actual = rbacController.getIT();
        assertEquals(expected,actual);
    }

    @Test
    public void  testGetEmails() throws Exception {
        String  expected = "Email Records Access";
        String actual = rbacController.getEmails();
        assertEquals(expected,actual);
    }

    @Test
    public void  testGetDataCenter() {
        String  expected = "Data Center Access";
        String actual = rbacController.getDataCenter();
        assertEquals(expected,actual);
    }

    @Test
    public void  testGetCustomerRecords() {
        String  expected = "Customer Records Access";
        String actual = rbacController.getCustomerRecords();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetSapRecords() {
       
        String  expected = "SAP Access";
        String actual = rbacController.getSapRecords();
        assertEquals(expected,actual);
    }

    @Test
    public void  testGetTimeCards() {
        String  expected = "timecards";
        String actual = rbacController.getTimeCards();
        assertEquals(expected,actual);
    }

}

