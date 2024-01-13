class Environment {
  testDbUrl!: string;
  portNumber!: number;
  ADMIN_TOKEN_JWT_KEY = "digital_menu";

  public getPort(): number {
    this.portNumber = 8000;
    return this.portNumber;
  }

  public getDbUrl(): string {
    this.testDbUrl = "mongodb+srv://dheemahiruchiTesting:XWyboc7XA16Vc9NQ@dheemahiruchitseting.e8wms.mongodb.net/DMR-testing?retryWrites=true&w=majority";
    return this.testDbUrl;
  }
}

export default new Environment();
