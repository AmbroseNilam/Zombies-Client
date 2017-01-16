package constants;

public enum Error {
	UNKNOWN_FAILURE("Error! Unknown Failure!"), INPUT_ERROR("Error! Invalid Input!"), RESPONSE_ERROR(
			"Error! No response from server!"), OFFLINE_ERROR("Error! Server is offline!"), AUTHENTICATION_ERROR(
					"Error! Unable to authenticate with server!");

	private String message;

	private Error(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
