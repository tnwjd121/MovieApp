package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

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
		System.out.println("----------------------------------  7: 영화 랭킹 리스트  ----------------------------------");
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
	
	public void movieRanking(List<MovieDto>movieDtos) {
		System.out.println("==================================== Movie Ranking ===================================");
		System.out.println("별점 랭킹");
		Map<Integer, List<MovieDto>> ratingMap = new HashMap<>();
		for(MovieDto movieDto : movieDtos) {
			int rating = movieDto.getRating();
			List<MovieDto> movies = ratingMap.getOrDefault(rating, new ArrayList<MovieDto>());
			movies.add(movieDto);
			ratingMap.put(rating, movies);
		}
		List<Integer> ratings = new ArrayList<>(ratingMap.keySet());
		Collections.sort(ratings,Collections.reverseOrder());
		
	    for (int i = 0; i < ratings.size(); i++) {
	        int rating = ratings.get(i);
	        List<MovieDto> movies = ratingMap.get(rating);
	        System.out.print((i + 1) + "위: " + rating + "점 (");
	        for (int j = 0; j < movies.size(); j++) {
	            System.out.print(movies.get(j).getMovieName());
	            if (j < movies.size() - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println(")");
		}
		
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("장르 랭킹");
		Map<String, List<MovieDto>>genreMap = new HashMap<>();
		
		for(MovieDto movieDto : movieDtos) {
			String genre = movieDto.getGenre();
			List<MovieDto> moviesByGenre = genreMap.getOrDefault(genre, new ArrayList<>());
			moviesByGenre.add(movieDto);
			genreMap.put(genre, moviesByGenre);
		}
		List<String> genres = new ArrayList<>(genreMap.keySet());
		Collections.sort(genres, (g1,g2) -> Integer.compare(genreMap.get(g2).size(), genreMap.get(g1).size()));
		
	    for (int i = 0; i < genres.size(); i++) {
	        String genre = genres.get(i);
	        List<MovieDto> movies = genreMap.get(genre);
	        System.out.print((i + 1) + "위: " + genre + " (");
	        for (int j = 0; j < movies.size(); j++) {
	            System.out.print(movies.get(j).getMovieName());
	            if (j < movies.size() - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println(")");
	    }
		System.out.println("======================================================================================");
	}
}
