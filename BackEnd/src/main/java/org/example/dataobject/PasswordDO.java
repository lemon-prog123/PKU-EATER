package org.example.dataobject;

public class PasswordDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.encrpt_password
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    private String encrptPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.usr_id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    private Integer usrId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.id
     *
     * @return the value of user_password.id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.id
     *
     * @param id the value for user_password.id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.encrpt_password
     *
     * @return the value of user_password.encrpt_password
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public String getEncrptPassword() {
        return encrptPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.encrpt_password
     *
     * @param encrptPassword the value for user_password.encrpt_password
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword == null ? null : encrptPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.usr_id
     *
     * @return the value of user_password.usr_id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public Integer getUsrId() {
        return usrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.usr_id
     *
     * @param usrId the value for user_password.usr_id
     *
     * @mbg.generated Sun Oct 02 14:27:31 CST 2022
     */
    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }
}