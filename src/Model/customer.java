package Model;

import java.sql.Blob;

public class customer {
	private String cmId;			//���
private String cmName;			//����
private String gender;	       //�Ա�
private String TelPhone;	   //�ֻ�����
private String birthday;		//��������
private Blob pic;

public void setCmId(String cmId) {
	this.cmId = cmId;
}
public String getCmId() {
	return cmId;
}
public String getTelPhone() {
	return TelPhone;
}
public void setCmName(String cmName) {
	this.cmName = cmName;
}
public String getCmName() {
	return cmName;
}
public String getBirthday() {
	return birthday;
}
public Blob getPic() {
	return this.pic;
}
public void setPic(Blob pic) {
	this.pic = pic;
}



public customer(String cmId, String cmName, String gender, String TelPhone, String birthday) {
	this.cmId=cmId;
	this.cmName=cmName;
	this.setGender(gender);
	this.TelPhone=TelPhone;
	this.birthday=birthday;
	
	
}

public customer(String cmId, String cmName, String gender, String TelPhone, String birthday,Blob pic) {
	this.cmId=cmId;
	this.cmName=cmName;
	this.setGender(gender);
	this.TelPhone=TelPhone;
	this.birthday=birthday;
	this.pic=pic;
	
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getGender() {
	return gender;
}

	
}







	

