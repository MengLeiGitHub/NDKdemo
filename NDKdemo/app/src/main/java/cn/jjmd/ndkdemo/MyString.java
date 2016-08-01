package cn.jjmd.ndkdemo;



public class MyString implements SingleSelectDialog.SingleModel {

	private String  item;
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public MyString(String string,int  type) {
		// TODO Auto-generated constructor stub
		this.type=type;
		item=string;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String getSelectItem() {
		// TODO Auto-generated method stub
		return getItem();
	}

}
