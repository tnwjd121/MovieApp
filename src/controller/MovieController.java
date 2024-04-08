package controller;

import dto.MovieDto;
import service.MovieRepository;
import service.MovieService;
import view.MovieView;

public class MovieController {
	
	private MovieService movieService = new MovieRepository();
	private MovieView movieView;

	public MovieController() {
		this.movieView = new MovieView(movieService);
	}
	
	public void runStartApp() {
		int selectNumber;
		while(true) {
			selectNumber = movieView.getMenu();
			switch (selectNumber) {
			case 0:
				return;
			case 1: 
				// 리뷰 등록
				createMovie();
				break;
			case 2: 
				// 리뷰 확인
				getMovieList();
				break;
			case 3: 
				// 리뷰 변경
				updateMovie();
				break;
			case 4: 
				// 리뷰 삭제
				deleteMovie();
				break;
			case 5: 
				// 해당하는 별점 이상 list 보여주기
				getRatingMovieList();
				break;
			case 6:
				// 해당하는 장르 list 보여주기
				getGenreMovieList();
				break;
			default:
				System.out.println("시스템에 없는 메뉴를 선택하였습니다. 다른 메뉴를 선택 부탁드립니다.");
				break;
			}
		}
	}
	public void createMovie() {
		int nextNumber = 0;
		MovieDto movieDto = movieView.createMovie(nextNumber);
		movieService.createMovie(movieDto);
		nextNumber++;
	}
	
	public void getMovieList() {
		movieView.getMoiveList(movieService.getMovieList());
	}
	
	public void updateMovie() {
		
		movieView.updateMovie();
	}
	
	public void deleteMovie() {
		int deleteNumber = movieView.deleteMovie();
		movieService.deleteMovie(deleteNumber);
	}
	
	public void getRatingMovieList() {
		movieView.getRatingMovieList(movieService.getRatingMovieList());
	}
	
	public void getGenreMovieList() {
		movieView.getGenreMovieList(movieService.getGenreMovieList());
	}
	
}
