package service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.MovieDto;

public class MovieRepository implements MovieService{
	
	private List<MovieDto> movieDtos = new ArrayList<>();
	private int nextNumber = 0;
	
	public MovieRepository() {
		loadData();
	}

	@Override
	public void createMovie(MovieDto movieDto) {
		movieDto.setMovieNumber(nextNumber);
		movieDtos.add(movieDto);
		saveData();
		nextNumber++;
		
	}
	@Override
	public List<MovieDto> getMovieList() {
		return movieDtos;
	}
	
	@Override
	public void updateMovie(int movieNumber, String movieName, String genre, int rating, String review) {
		MovieDto movieUpdate = null;
		for(MovieDto movie : movieDtos) {
			if(movie.getMovieNumber()== movieNumber) {
				movieUpdate = movie;
				break;
			}
		}
		if (movieUpdate != null) {
			movieUpdate.setMovieName(movieName);
			movieUpdate.setGenre(genre);
			movieUpdate.setRating(rating);
			movieUpdate.setReview(review);
			saveData();
			System.out.println("영화리뷰가 성공적으로 업데이트되었습니다.");
		}else {
			System.out.println("해당 번호의 영화리뷰를 찾을 수 없습니다.");
		}
		
	}
	@Override
	public void deleteMovie(int deleteNumber) {
		MovieDto movieDelete = null;
		for(MovieDto movie : movieDtos) {
			if(movie.getMovieNumber() == deleteNumber) {
				movieDelete = movie;
				break;
			}
		}
		if(movieDelete!= null) {
			movieDtos.remove(movieDelete);
			saveData();
			System.out.println("영화 리뷰가 성공적으로 삭제되었습니다.");
			System.out.println("======================================================================================");
		}else {
		System.out.println("해당 번호의 영화리뷰를 찾을 수 없습니다.");
		System.out.println("======================================================================================");
		}
	}
	@Override
	public List<MovieDto> getRatingMovieList() {
		return movieDtos;
	}
	@Override
	public List<MovieDto> getGenreMovieList() {
		return movieDtos;
	}
	private void saveData() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("movie_data.txt"))){
			for(MovieDto movieDto : movieDtos) {
				writer.println(movieDto.getMovieNumber() + "," + movieDto.getMovieName() + ","+ movieDto.getGenre() + ","+ movieDto.getRating() + ","+ movieDto.getReview());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadData() {
		try (Scanner scanner = new Scanner(new File("movie_data.txt"))){
			int maxNumber = 0;
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(",");
				int number = Integer.parseInt(data[0]);
				if(number > maxNumber) {
					maxNumber = number;
				}
			String movieName = data[1];
			String genre = data[2];
			int rating = Integer.parseInt(data[3]);
			String review = data[4];
			movieDtos.add(new MovieDto(number, movieName, genre, rating, review));
			}
			nextNumber = maxNumber + 1 ;
		} catch (Exception e) {
			System.out.println("게임 데이터 파일이 없습니다. 새로운 파일을 생성합니다.");
		}
	}





}
