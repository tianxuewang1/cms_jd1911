package com.briup.apps.cms.bean;

public class Test {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test.id
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test.name
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test.id
     *
     * @return the value of test.id
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test.id
     *
     * @param id the value for test.id
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test.name
     *
     * @return the value of test.name
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test.name
     *
     * @param name the value for test.name
     *
     * @mbg.generated Mon Nov 11 15:58:35 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
    
}