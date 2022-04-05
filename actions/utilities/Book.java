package utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private Integer id;
	private String title;
	private Integer quantity;
	private Double price;
	private Double totalMoney;

	public Book() {
		super();
	}

	public Book(Integer id, String title, int quantity, Double price) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

}
