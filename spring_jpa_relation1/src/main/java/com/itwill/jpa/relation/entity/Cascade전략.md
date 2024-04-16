영속성 전이(Cascade)란?
	-부모 엔티티가 영속화될 때 자식 엔티티도 같이 영속화되고, 부모 엔티티가 삭제될 때 자식 엔티티도 삭제되는 등 
		특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 전이되는 것을 의미한다.
	-JPA에서는 이러한 연관된 엔티티간의 의존성(dependency)을 설정하기 위해 
		Enum 타입의 javax.persistence.CascadeType 을 제공하고 있다.


CascadeType의 종류

	- CascadeType.ALL
		모든 Cascade를 적용한다.
		
	- CascadeType.PERSIST
		엔티티를 영속화할 때, 연관된 하위 엔티티도 함께 유지한다.
		
	- CascadeType.MERGE
		엔티티 상태를 병합(Merge)할 때, 연관된 하위 엔티티도 모두 병합한다.
		
	- CascadeType.REMOVE
		엔티티를 제거할 때, 연관된 하위 엔티티도 모두 제거한다.
		
	- CascadeType.DETACH
		영속성 컨텍스트에서 엔티티 제거
	 	부모 엔티티를 detach() 수행하면, 연관 하위 엔티티도 detach()상태가 되어 변경 사항을 반영하지 않는다.
	
	- CascadeType.REFRESH
		상위 엔티티를 새로고침(Refresh)할 때, 연관된 하위 엔티티도 모두 새로고침한다.
	
	- CasecadeType.Remove와 orphanRemoval = true의 차이점
		
		CasecadeType.Remove는 부모 엔티티가 삭제되면 자식 엔티티도 삭제된다.	즉, 부모가 자식의 삭제 생명 주기를 관리한다.
			만약 CascadeType.REFRESH도 함께 사용하면, 부모가 자식의 전체 생명 주기를 관리하게 된다.
			그러나 이 옵션의 경우, 부모 엔티티가 자식 엔티티와의 관계를 제거해도 자식 엔티티는 삭제되지 않고 그대로 남아있다.
	
		orphanRemoval = true 또한 부모 엔티티가 삭제되면 자식 엔티티도 삭제 된다.
			그런데 CasecadeType.Remove와는 달리, 부모 엔티티가 자식 엔티티와의 관계를 제거하면 자식은 고아로 취급되어 그대로 사라진다.
	
	두 옵션은 부모 엔티티를 삭제할 때는 동일하게 동작하지만, 부모 엔티티에서 자식 엔티티와의 관계를 제거할 때 차이점을 가진다.
	