package com.lomtom.website.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-09 20:20:01
 */
@Data
public class ProfileEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String profilePicpath;
	/**
	 * 
	 */
	private Integer profileId;
	/**
	 * 
	 */
	private String profileIntro;
	/**
	 * 
	 */
	private String profileCnname;
	/**
	 * 
	 */
	private String profileEnname;
	/**
	 * 
	 */
	private String profileEducation;
	/**
	 * 
	 */
	private String profilePosition;
	/**
	 * 
	 */
	private String profileTelephone;
	/**
	 * 
	 */
	private String profileEmail;
	/**
	 * 
	 */
	private String profileStudydir;
	/**
	 * 
	 */
	private String profileAddress;
	/**
	 * 
	 */
	private String profileWorkexp;
	/**
	 * 
	 */
	private String profileEdubackground;



	private Boolean profileStatus;


	private Boolean isHome;

	private String project;

	private Integer weight;

}
