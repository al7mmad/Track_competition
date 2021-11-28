
public class Student {
	String teamNum;
	String teamName;
	String name;
	String id;
	String major;
	String rank;
	
	public Student() {}

	public Student(String name, String id, String major, String rank, String teamNum, String teamName) {

		this.teamNum = teamNum;
		this.teamName = teamName;
		this.name = name;
		this.id = id;
		this.major = major;
		this.rank = rank;
	}
	

	@Override
	public String toString() {
		return "Student [teamNum=" + teamNum + ", teamName=" + teamName + ", name=" + name + ", id=" + id + ", major="
				+ major + ", rank=" + rank + "]";
	}

}
