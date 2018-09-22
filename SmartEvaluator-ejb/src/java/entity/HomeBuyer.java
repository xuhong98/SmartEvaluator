/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author hongxu
 */
@Entity
public class HomeBuyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private Long savings;
    
    private Long monthlyIncome;
    
    private Long monthlyExpense;
    
    private Integer age;
    
    private Long estimatedLoan;
    
    private Integer loanRepaymentYear;
    
    private Integer homeSize;
    
    private Boolean married;

    public HomeBuyer() {
    }

    public HomeBuyer(Long savings, Long monthlyIncome, Long monthlyExpense, Integer age, Integer loanRepaymentYear, Integer homeSize, Boolean married) {
        this.savings = savings;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpense = monthlyExpense;
        this.age = age;
        this.loanRepaymentYear = loanRepaymentYear;
        this.homeSize = homeSize;
        this.married = married;
    }

    public Integer getHomeSize() {
        return homeSize;
    }

    public void setHomeSize(Integer homeSize) {
        this.homeSize = homeSize;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getLoanRepaymentYear() {
        return loanRepaymentYear;
    }

    public void setLoanRepaymentYear(Integer loanRepaymentYear) {
        this.loanRepaymentYear = loanRepaymentYear;
    }
    
    public Long getSavings() {
        return savings;
    }

    public void setSavings(Long savings) {
        this.savings = savings;
    }

    public Long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Long getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(Long monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getEstimatedLoan() {
        return estimatedLoan;
    }

    public void setEstimatedLoan(Long estimatedLoan) {
        this.estimatedLoan = estimatedLoan;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HomeBuyer)) {
            return false;
        }
        HomeBuyer other = (HomeBuyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HomeBuyer[ id=" + id + " ]";
    }
    
}
