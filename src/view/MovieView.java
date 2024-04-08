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
		System.out.println("================================== Movie Review App ==================================");
		System.out.println("----------------------------------       MENU       ----------------------------------");
		System.out.println("----------------------------------  0: 프로그램 종료    ----------------------------------");
		System.out.println("----------------------------------  1: 영화 리뷰 등록   ----------------------------------");
		System.out.println("----------------------------------  2: 영화 리뷰 리스트  ----------------------------------");
		System.out.println("----------------------------------  3: 영화 리뷰 수정   ----------------------------------");
		System.out.println("----------------------------------  4: 영화 리뷰 삭제   ----------------------------------");
		System.out.println("----------------------------------  5: 영화 별점 리스트  ----------------------------------");
		System.out.println("----------------------------------  6: 영화 장르 리스트  ----------------------------------");
		System.out.println("======================================================================================");
		System.err.print("Choice Menu: ");
		int selectNumber = -1;
		selectNumber = sc.nextInt();
		sc.nextLine();
		return selectNumber;
	}
	
	public MovieDto createMovie(int movieNumber) {
		System.out.println("================================= Create Movie Review =================================");
		System.out.print("영화명: ");
		String movieName = sc.nextLine();
		System.out.print("장르: ");
		String genre = sc.nextLine();
		System.out.print("별점(1~5까지 입력): ");
		int rating = sc.nextInt();
		sc.nextLine();
		System.out.print("한줄평: ");
		String review = sc.nextLine();
		System.out.println("======================================================================================");
		return new MovieDto(movieNumber, movieName, genre, rating, review);
	}
	
	public void getMoiveList(List<MovieDto>movieDtos) {
		System.out.println("================================== Movie Review List =================================");
		for(MovieDto movieDto : movieDtos) {
			System.out.println(movieDto.toString());
		}
		System.out.println("======================================================================================");
	}
	
	public void updateMovie() {
		System.out.println("================================= Update Movie Review ================================");
		System.out.print("Update Movie Number: ");
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
			System.out.println("======================================================================================");
		}else {
			System.out.println("해당 번호의 영화 리뷰를 찾을 수 없습니다.");
			System.out.println("======================================================================================");
		}
	}
	
	public int deleteMovie() {
		System.out.println("================================= Delete Movie Review ================================");
		System.out.print("Delete Movie Number: ");
		int deleteNumber = sc.nextInt();
		sc.nextLine();
		return deleteNumber;
	}
	public void getRatingMovieList(List<MovieDto>movieDtos) {
		System.out.println("================================== Movie Rating List =================================");
		System.out.print("별점(1~5까지 입력): ");
		int findRating = sc.nextInt();
		sc.nextLine();
		for(MovieDto movieDto : movieDtos) {
			if(movieDto.getRating()>=findRating) {
				System.out.println(movieDto.toString());
			}
		}
		System.out.println("======================================================================================");
	}
	public void getGenreMovieList(List<MovieDto>movieDtos) {
		System.out.println("================================== Movie Genre List =================================");
		System.out.print("장르: ");
		String findGenre = sc.nextLine();
		for(MovieDto movieDto : movieDtos) {
			if(movieDto.getGenre().equals(findGenre)){
				System.out.println(movieDto.toString());
			}
		}
		System.out.println("======================================================================================");
	}

}
