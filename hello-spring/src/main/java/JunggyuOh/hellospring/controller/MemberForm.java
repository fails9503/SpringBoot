package JunggyuOh.hellospring.controller;

// input태그의 name이라는 필드와 자동으로 매칭됨
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
