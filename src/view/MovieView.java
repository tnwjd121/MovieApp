package view;

import java.util.List;
import java.util.Scanner;

import dto.MovieDto;

public class MovieView {
	
	Scanner sc = new Scanner(System.in);
	
	public int getMenu() {
		System.out.println("영화 리뷰앱입니다.");
		System.out.println("0. 종료");
		System.out.println("1.영화리뷰 등록");
		System.out.println("2.영화리뷰 확인");
		System.out.println("3.영화리뷰 수정");
		System.out.println("4.영화리뷰 삭제");
		int selectNumber = -1;
		selectNumber = sc.nextInt();
		sc.nextLine();
		return selectNumber;
	}
	
	public MovieDto createMovie(int number) {
		System.out.print("영화명: ");
		String movieName = sc.nextLine();
		System.out.println("장르: ");
		String genre = sc.nextLine();
		System.out.println("별점(1~5까지 입력): ");
		int rating = sc.nextInt();
		sc.nextLine();
		System.out.println("한줄평: ");
		String review = sc.nextLine();
		return new MovieDto(number, movieName, genre, rating, review);
	}
	

}
