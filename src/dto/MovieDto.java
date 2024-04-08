package dto;

public class MovieDto {
	
	private int movieNumber;
	private String movieName;
	private String genre;
	private int rating;
	private String review;
	public int getMovieNumber() {
		return movieNumber;
	}
	public void setMovieNumber(int movieNumber) {
		this.movieNumber = movieNumber;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	
	public MovieDto(int movieNumber, String movieName, String genre, int rating, String review) {
		this.movieNumber = movieNumber;
		this.movieName = movieName;
		this.genre = genre;
		this.rating = rating;
		this.review = review;
	}
	@Override
	public String toString() {
		return "MovieDto [movieNumber=" + movieNumber + ", movieName=" + movieName + ", genre=" + genre + ", rating="
				+ rating + ", review=" + review + "]";
	}
	
	

}
