package controller;

import dto.MovieDto;
import service.MovieRepository;
import service.MovieService;
import view.MovieView;

public class MovieController {
	
	private MovieService movieService = new MovieRepository();
	private MovieView movieView;
	private int nextNumber = 0;

	public MovieController() {
		this.movieView = new MovieView();
	}
	
	public void runStartApp() {
		int selectNumber;
		while(true) {
			selectNumber = movieView.getMenu();
			switch (selectNumber) {
			case 0:
				// 앱 종료하면서 데이터 저장
				return;
			case 1: 
				createMovie();
				break;
			case 2: 
				// 리뷰 확인
				break;
			case 3: 
				// 리뷰 변경
				break;
			case 4: 
				// 리뷰 삭제
				break;

			default:
				System.out.println("시스템에 없는 메뉴를 선택하였습니다. 다른 메뉴를 선택 부탁드립니다.");
				break;
			}
		}
	}
	public void createMovie() {
		MovieDto movieDto = movieView.createMovie(nextNumber);
		movieService.createMovie(movieDto);
		nextNumber++;
	}
	
	
	
	
	
	
	

}
