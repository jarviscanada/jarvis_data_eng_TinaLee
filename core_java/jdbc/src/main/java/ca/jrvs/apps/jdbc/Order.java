package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order implements DataTransferObject {
  private String customerFirstName;
  private String customerLastName;
  private String customerEmail;
  private long id;
  private Date creationDate;
  private BigDecimal totalDue;
  private String status;
  private String salespersonFirstName;
  private String salespersonLastName;
  private String salespersonEmail;
  private List<OrderLine> orderItems;

  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public String getCustomerLastName() {
    return customerLastName;
  }

  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  @Override
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public BigDecimal getTotalDue() {
    return totalDue;
  }

  public void setTotalDue(BigDecimal totalDue) {
    this.totalDue = totalDue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSalespersonFirstName() {
    return salespersonFirstName;
  }

  public void setSalespersonFirstName(String salespersonFirstName) {
    this.salespersonFirstName = salespersonFirstName;
  }

  public String getSalespersonLastName() {
    return salespersonLastName;
  }

  public void setSalespersonLastName(String salespersonLastName) {
    this.salespersonLastName = salespersonLastName;
  }

  public String getSalespersonEmail() {
    return salespersonEmail;
  }

  public void setSalespersonEmail(String salespersonEmail) {
    this.salespersonEmail = salespersonEmail;
  }

  public List<OrderLine> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderLine> orderItems) {
    this.orderItems = orderItems;
  }

  @Override
  public String toString() {
    return "Order{" +
        "customerFirstName='" + customerFirstName + '\'' +
        ", customerLastName='" + customerLastName + '\'' +
        ", customerEmail='" + customerEmail + '\'' +
        ", id=" + id +
        ", creationDate=" + creationDate +
        ", totalDue=" + totalDue +
        ", status='" + status + '\'' +
        ", salespersonFirstName='" + salespersonFirstName + '\'' +
        ", salespersonLastName='" + salespersonLastName + '\'' +
        ", salespersonEmail='" + salespersonEmail + '\'' +
        ", orderItems=" + orderItems +
        '}';
  }
}
