package com.doubleDB.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "test_database")
@Data
public class ResponseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "acquirecode")
	private String acquirecode;
	@Column(name = "amount", precision = 10, scale = 2)
	private BigDecimal amount;
	@Column(name = "cardnumber")
	private String cardnumber;
	@Column(name = "processing_code")
	private String processing_code;
	// @Column(name="startdate")
	// private Date startdate;
	@Column(name = "rrn")
	private String rrn;
	@Column(name = "stan")
	private String stan;
	// @Column(name="enddate")
	// private Date enddate;
	@Column(name = "response_code")
	private String response_code;
	@Column(name = "mid")
	private String mid;
	@Column(name = "tid")
	private String tid;
	@Column(name = "currency_code")
	private String currency_code;
	@Column(name = "is_reversed")
	private Boolean isReversed;
	@Column(name = "is_void")
	private Boolean isVoid;
	@Column(name = "txntype")
	private String txntype;
	@Column(name = "cardtype")
	private String cardtype;
	@Column(name = "location")
	private String location;
	@Column(name = "expdate")
	private String expdate;
	@Column(name = "req_in_time")
	private Date req_in_time;
	@Column(name = "req_out_time")
	private Date req_out_time;
	@Column(name = "request_isomsg", columnDefinition = "TEXT", length = 1000)
	private String requestIsomsg;
	@Column(name = "response_isomsg", columnDefinition = "TEXT", length = 1000)
	private String resIsomsg;
	@Column(name = "chipdata_req", columnDefinition = "TEXT", length = 1000)
	private String chipdatareq;
	@Column(name = "chipdata_res", columnDefinition = "TEXT", length = 1000)
	private String chipdatares;
	@Column(name = "is_credit")
	private boolean iscredit;
	@Column(name = "wallet_ledger_id")
	private String walletledgerid;
	@Column(name = "wallet_status")
	private String walletstatus;
	@Column(name = "wallet_status_desc")
	private String walletstatusdesc;
	@Column(name = "walletid")
	private Long walletid;

	@Column(name = "status")
	private String status;
	@Column(name = "status_code")
	private Integer statusCode;
	@Column(name = "status_description")
	private String statusDescription;

	@Column(name = "error_description")
	private String errorDescription;

	@Column(name = "bank_transaction_id")
	private String finoTransactionID;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "wallet_reversed")
	private Boolean walletReversed;

	@Column(name = "bank_reversed")
	private Boolean bankReversed;

}
