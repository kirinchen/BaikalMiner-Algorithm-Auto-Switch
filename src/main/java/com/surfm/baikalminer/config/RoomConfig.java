package com.surfm.baikalminer.config;

public class RoomConfig {

	// pool[0].algo=cryptonight-lite
	// pool[0].coin=AEON
	// pool[0].dataSource=whattomine
	// pool[0].url=stratum+tcp://pool.aeonminingpool.com:3333
	// pool[0].user=WmsbQyCzbsZ2dekyB21aG9FpcnF6uraGejHDGqa2zBfqXAJvgY2mJuPhFaaEttMUt9c61Em6dP1WeHkyDtyRgWf11Q6NzPzDn
	// pool[0].pass=5aSnTg==

	private Algo algo;
	private String coin;
	private DataSource dataSource;
	private String url;
	private String user;
	private String pass;

	public Algo getAlgo() {
		return algo;
	}

	public void setAlgo(Algo algo) {
		this.algo = algo;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
