package com.sk.mba.roianfwk.transfer;

import java.math.BigDecimal;

public class EPLcommonCDTO
{
  private String accountNumber;
  private String bankCode;
  private String branchCode;
  private String accountGroup;
  private String accountGroupKind;
  private String CIFName;
  private String CIFNo;
  private String openDate;
  private String currency;
  private String lastCreditDate;

  private String lastDebitDate;
  private String status;

  private String feeCurrency;
  
private String remarks;
  private String glCode;
  private String expireDate;
  private String interestIndex;

  private int lastPbLine=0;
  private int pageNumber;
  private int linePerPage;
  private int sequenceNumber;
  private String oldAccountNumber;
  private String securityNo;
  private String issueKind;
  private String openBranchName;
  private String openBranchTelNo;
  private String idNo;

  private String trxNo;
  private String passbookNo;
  private int count;
  private String secondaryAccount;
  private int endLine;
  private String intCalcStrtdate;
  private String pbKind;
  private String checkNumber;
  private int lastPbLine1;
  private String inclusionFee;
  private String calculationFee;
  private String pbIssueKind;
  private int pbIssueCount;
  private String pbMsVersionPb;
  private String pbMsVersionDb;
  private int pbLinePerPage;
  private int pbSkipPerPage;
  private String renewDate;

  public EPLcommonCDTO()
  {
    accountNumber="*";
    bankCode="*";
    branchCode="*";
    accountGroup="*";
    accountGroupKind="*";
    CIFName="*";
    CIFNo="*";
    openDate="*";
    currency="*";

    lastCreditDate="*";

    lastDebitDate="*";

    status="*";

    feeCurrency="*";

    remarks="*";
    glCode="*";
    expireDate="*";
    interestIndex="*";


    int lastPbLine=0;
    int pageNumber=0;
    int linePerPage=0;

    int sequenceNumber=0;
    oldAccountNumber="*";
    securityNo="*";
    issueKind="*";
    openBranchName="*";
    openBranchTelNo="*";
    idNo="*";

    trxNo="*";
    passbookNo="*";
    int count=0;
    secondaryAccount="*";
    int endLine=0;
    intCalcStrtdate="*";
    pbKind="*";
    checkNumber="*";
    int lastPbLine1=0;
    inclusionFee="*";
    calculationFee="*";
    pbIssueKind="*";
    int pbIssueCount=0;
    pbMsVersionPb="*";
    pbMsVersionDb="*";
    int pbLinePerPage=0;
    int pbSkipPerPage=0;
    renewDate="*";

  }
  public String getCheckNumber()
  {
    return checkNumber;
  }
  public void setCheckNumber(String checkNumber)
  {
    this.checkNumber = checkNumber;
  }
  public String getAccountNumber() {
    return accountNumber;
  }
  public String getBankCode() {
    return bankCode;
  }
  public String getAccountGroup() {
    return accountGroup;
  }

  public String getCurrency() {
    return currency;
  }
  public String getCIFNo() {
    return CIFNo;
  }
  
  public String getOpenDate() {
    return openDate;
  }
  public String getRenewDate() {
    return renewDate;
  }
  public String getStatus() {
    return status;
  }
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }
  public void setAccountGroup(String accountGroup) {
    this.accountGroup = accountGroup;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }
  public void setCIFNo(String CIFNo) {
    this.CIFNo = CIFNo;
  }
  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }
  public void setRenewDate(String renewDate) {
    this.renewDate = renewDate;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setCIFName(String CIFName)
  {
    this.CIFName = CIFName;
  }
  public String getCIFName()
  {
    return CIFName;
  }
  public void setLastDebitDate(String lastDebitDate)
  {
    this.lastDebitDate = lastDebitDate;
  }
  public String getLastDebitDate()
  {
    return lastDebitDate;
  }
  public void setLastCreditDate(String lastCreditDate)
  {
    this.lastCreditDate = lastCreditDate;
  }
  public String getLastCreditDate()
  {
    return lastCreditDate;
  }
  public String getAccountGroupKind()
  {
    return accountGroupKind;
  }
  public void setAccountGroupKind(String accountGroupKind)
  {
    this.accountGroupKind = accountGroupKind;
  }
  public String getBranchCode()
  {
    return branchCode;
  }
  public void setBranchCode(String branchCode)
  {
    this.branchCode = branchCode;
  }

  public String getFeeCurrency()
  {
    return feeCurrency;
  }

  public void setFeeCurrency(String feeCurrency)
  {
    this.feeCurrency = feeCurrency;
  }



  public String getRemarks()
  {
    return remarks;
  }
  public void setRemarks(String remarks)
  {
    this.remarks = remarks;
  }
  public String getGlCode()
  {
    return glCode;
  }
  public void setGlCode(String glCode)
  {
    this.glCode = glCode;
  }
  public void setExpireDate(String expireDate)
  {
    this.expireDate = expireDate;
  }
  public String getExpireDate()
  {
    return expireDate;
  }
  public void setInterestIndex(String interestIndex)
  {
    this.interestIndex = interestIndex;
  }
  public String getInterestIndex()
  {
    return interestIndex;
  }




  public int getLastPbLine() {
    return lastPbLine;
  }
  public int getLastPbLine1() {
    return lastPbLine1;
  }
  public int getLinePerPage() {
    return linePerPage;
  }
  public void setLastPbLine(int lastPbLine) {
    this.lastPbLine = lastPbLine;
  }
  public void setLastPbLine1(int lastPbLine1) {
    this.lastPbLine1 = lastPbLine1;
  }
  public void setLinePerPage(int linePerPage) {
    this.linePerPage = linePerPage;
  }
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
  public int getPageNumber() {
    return pageNumber;
  }

  public void setSequenceNumber(int sequenceNumber)
  {
    this.sequenceNumber = sequenceNumber;
  }
  public int getSequenceNumber()
  {
    return sequenceNumber;
  }
  public String getOldAccountNumber()
  {
    return oldAccountNumber;
  }
  public void setOldAccountNumber(String oldAccountNumber)
  {
    this.oldAccountNumber = oldAccountNumber;
  }
  public String getSecurityNo()
  {
    return securityNo;
  }
  public void setSecurityNo(String securityNo)
  {
    this.securityNo = securityNo;
  }
  public void setIssueKind(String issueKind)
  {
    this.issueKind = issueKind;
  }
  public String getIssueKind()
  {
    return issueKind;
  }
  public void setOpenBranchName(String openBranchName)
  {
    this.openBranchName = openBranchName;
  }
  public String getOpenBranchName()
  {
    return openBranchName;
  }
  public void setOpenBranchTelNo(String openBranchTelNo)
  {
    this.openBranchTelNo = openBranchTelNo;
  }
  public String getOpenBranchTelNo()
  {
    return openBranchTelNo;
  }
  public void setIdNo(String idNo)
  {
    this.idNo = idNo;
  }
  public String getIdNo()
  {
    return idNo;
  }
  public void setTrxNo(String trxNo)
  {
    this.trxNo = trxNo;
  }
  public String getTrxNo()
  {
    return trxNo;
  }
  public void setPassbookNo(String passbookNo) {
    this.passbookNo = passbookNo;
  }
  public String getPassbookNo() {
    return passbookNo;
  }
  public void setCount(int count)
  {
    this.count = count;
  }
  public int getCount()
  {
    return count;
  }
  public void setSecondaryAccount(String secondaryAccount)
  {
    this.secondaryAccount = secondaryAccount;
  }
  public String getSecondaryAccount()
  {
    return secondaryAccount;
  }
  public void setEndLine(int endLine)
  {
    this.endLine = endLine;
  }
  public int getEndLine()
  {
    return endLine;
  }
  public void setIntCalcStrtdate(String intCalcStrtdate)
  {
    this.intCalcStrtdate = intCalcStrtdate;
  }
  public String getIntCalcStrtdate()
  {
    return intCalcStrtdate;
  }
  public void setPbKind(String pbKind)
  {
    this.pbKind = pbKind;
  }
  public String getPbKind()
  {
    return pbKind;
  }
  public String getCalculationFee()
  {
    return calculationFee;
  }
  public void setCalculationFee(String calculationFee)
  {
    this.calculationFee = calculationFee;
  }
  public void setInclusionFee(String inclusionFee)
  {
    this.inclusionFee = inclusionFee;
  }
  public String getInclusionFee()
  {
    return inclusionFee;
  }
  public String getPbIssueKind()
  {
    return pbIssueKind;
  }
  public void setPbIssueKind(String pbIssueKind)
  {
    this.pbIssueKind = pbIssueKind;
  }
  public int getPbIssueCount()
  {
    return pbIssueCount;
  }
  public void setPbIssueCount(int pbIssueCount)
  {
    this.pbIssueCount = pbIssueCount;
  }
  public String getPbMsVersionPb()
  {
    return pbMsVersionPb;
  }
  public void setPbMsVersionPb(String pbMsVersionPb)
  {
    this.pbMsVersionPb = pbMsVersionPb;
  }
  public String getPbMsVersionDb()
  {
    return pbMsVersionDb;
  }
  public void setPbMsVersionDb(String pbMsVersionDb)
  {
    this.pbMsVersionDb = pbMsVersionDb;
  }
  public int getPbLinePerPage()
  {
    return pbLinePerPage;
  }
  public void setPbLinePerPage(int pbLinePerPage)
  {
    this.pbLinePerPage = pbLinePerPage;
  }
  public int getPbSkipPerPage()
  {
    return pbSkipPerPage;
  }

  public void setPbSkipPerPage(int pbSkipPerPage)
  {
    this.pbSkipPerPage = pbSkipPerPage;
  }

  private java.util.List pageList;


}