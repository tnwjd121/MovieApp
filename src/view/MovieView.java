package view;

import java.util.List;
import java.util.Scanner;

import dto.MovieDto;
import service.MovieService;

public class MovieView {
	
	Scanner sc = new Scanner(System.in);
	private MovieService movieService;
	
	public MovieView(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public int getMenu() {
		System.out.println("영화 리뷰앱입니다.");
		System.out.println("0. 종료");
		System.out.println("1.영화리뷰 등록");
		System.out.println("2.영화리뷰 확인");
		System.out.println("3.영화리뷰 수정");
		System.out.println("4.영화리뷰 삭제");
		System.out.println("5.영화별점 리스트");
		System.out.println("6.영화장르 리스트");
		int selectNumber = -1;
		selectNumber = sc.nextInt();
		sc.nextLine();
		return selectNumber;
	}
	
	public MovieDto createMovie(int movieNumber) {
		System.out.print("영화명: ");
		String movieName = sc.nextLine();
		System.out.print("장르: ");
		String genre = sc.nextLine();
		System.out.print("별점(1~5까지 입력): ");
		int rating = sc.nextInt();
		sc.nextLine();
		System.out.print("한줄평: ");
		String review = sc.nextLine();
		return new MovieDto(movieNumber, movieName, genre, rating, review);
	}
	
	public void getMoiveList(List<MovieDto>movieDtos) {
		System.out.println("----------영화리뷰----------");
		for(MovieDto movieDto : movieDtos) {
			System.out.println(movieDto.toString());
		}
	}
	
	public void updateMovie() {
		System.out.println("수정할 영화번호를 입력하세요.");
		int updateNumber = sc.nextInt();
		sc.nextLine();
		MovieDto movieUpdate = null;
		for(MovieDto movie : movieService.getMovieList()) {
			if(movie.getMovieNumber() == updateNumber) {
				movieUpdate = movie;
				break;
			}
		}
		if (movieUpdate != null) {
			System.out.println("영화리뷰를 수정하세요.");
			System.out.print("영화명: ");
			String movieName = sc.nextLine();
			System.out.print("장르: ");
			String genre = sc.nextLine();
			System.out.print("별점(1~5까지 입력): ");
			int rating = sc.nextInt();
			sc.nextLine();
			System.out.print("한줄평: ");
			String review = sc.nextLine();
			movieService.updateMovie(updateNumber, movieName, genre, rating, review);
		}else {
			System.out.println("해당 번호의 게임을 찾을 수 없습니다.");
		}
	}
	
	public int deleteMovie() {
		System.out.println("삭제할 영화번호를 입력하세요.");
		int deleteNumber = sc.nextInt();
		sc.nextLine();
		return deleteNumber;
	}
	public void getRatingMovieList(List<MovieDto>movieDtos) {
		System.out.println("입력하는 별점 이상의 영화 리스트만 확인");
		System.out.print("별점(1~5까지 입력): ");
		int findRating = sc.nextInt();
		sc.nextLine();
		for(MovieDto movieDto : movieDtos) {
			if(movieDto.getRating()>=findRating) {
				System.out.println(movieDto.toString());
			}
		}
	}
	public void getGenreMovieList(List<MovieDto>movieDtos) {
		System.out.println("입력하는 장르의 영화리스트만 확인");
		System.out.println("장르: ");
		String findGenre = sc.nextLine();
		for(MovieDto movieDto : movieDtos) {
			if(movieDto.getGenre().equals(findGenre)){
				System.out.println(movieDto.toString());
			}
		}
	}

}
