<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header bg-primary text-white">
					<h5 class="modal-title" id="exampleModalLabel">Send Mail</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="from">From:</label> <input type="email"
								class="form-control" id="from" readonly
								value="etfbl.ip.app@gmail.com">
						</div>
						<div class="form-group">
							<label for="recipient">To:</label> <input type="email"
								class="form-control" id="recipient" readonly>
						</div>
						<div class="form-group">
							<label for="subject">Subject:</label> <input type="text"
								class="form-control" id="subject" readonly>
						</div>
						<div class="form-group">
							<label for="message">Message:</label>
							<textarea class="form-control" id="message" rows="3" readonly></textarea>
						</div>
						<div class="form-group">
							<label for="answer">Your answer is:</label>
							<textarea class="form-control" id="answer" rows="3"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="sendMail()">Send
						Mail</button>
				</div>
			</div>
		</div>
	</div>