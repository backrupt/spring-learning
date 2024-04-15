Fetch Type 이란
	Fetch Type 은 JPA 가 하나의 Entity 를 조회할 때, 연관관계에 있는 객체들을 	어떻게 가져올 것이냐를 나타내는 설정값입니다.

Fetch Type 은 크게 Eager 와 Lazy 두가지 전략이 있습니다. Fetch Type Issue 	상황이라는 것은 하나의 Entity 를 로드할 때, 아래의 두가지 전략 중 고민하는 	상황을 말합니다.

 - 연관 관계에 있는 Entity 들 모두 가져온다 → Eager 전략
 - 연관 관계에 있는 Entity 가져오지 않고, getter로 접근할 때 가져온다 → Lazy 전략